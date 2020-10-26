package com.companyname.filereport;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

import com.companyname.filereport.domain.service.DataService;
import com.companyname.filereport.dto.ReportDTO;
import com.companyname.filereport.exception.InvalidDirectoryException;
import com.companyname.filereport.exception.InvalidFormatException;
import com.companyname.filereport.exception.InvalidLineException;

/**
 * 
 * @author Bruno.cintra
 *
 */
public class FileReport {

	public static void main(String[] args) throws IOException, InterruptedException, InvalidDirectoryException {
		new FileReport().processFiles(System.getenv("HOMEPATH"));
	}

	public void processFiles(String homepath) throws InterruptedException, IOException, InvalidDirectoryException {

		WatchService watchService = FileSystems.getDefault().newWatchService();

		if(homepath == null) {
			throw new InvalidDirectoryException("variável de ambiente HOMEPATH não criada");
		}
		
		if (Files.exists(Paths.get(homepath)) == false) {
			throw new InvalidDirectoryException(homepath + " não existe");
		}

		Path inputPath = Paths.get(homepath + File.separator + "data" + File.separator + "in");

		if (Files.exists(inputPath) == false) {
			throw new InvalidDirectoryException(inputPath.toString());
		}

		Path outputPath = Paths.get(homepath + File.separator + "data" + File.separator + "out");

		if (Files.exists(outputPath) == false) {
			throw new InvalidDirectoryException(outputPath.toString());
		}

		inputPath.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

		System.out.println(String.format("Monitorando diretório de entrada: %s.", inputPath.toString()));

		DataService service = new DataService();
		WatchKey key;
		while ((key = watchService.take()) != null) {
			for (WatchEvent<?> event : key.pollEvents()) {

				String filename = event.context().toString();

				System.out.println(String.format("Arquivo encontrado: %s.", filename));

				String fileExtension = filename.substring(filename.length() - 4);

				if (".dat".equalsIgnoreCase(fileExtension)) {

					System.out.println(String.format("Iniciando processamento do arquivo: %s.", filename));

					Path inputFilePath = inputPath.resolve((Path) event.context());
					Path outputFilePath = outputPath.resolve(filename.replace(".dat", ".done.dat"));

					try {
						List<String> lines = Files.readAllLines(inputFilePath);

						ReportDTO report;
						report = service.generateReportData(lines);

						try (BufferedWriter writer = Files.newBufferedWriter(outputFilePath)) {

							System.out.println(String.format("Gerando arquivo de saída: %s.", filename));

							writer.write(String.format("%dç%dç%02dç%s", report.getInFileCustomersQuantity(),
									report.getInFileSalesmanQuantity(), report.getMostExpensiveSalesId(),
									report.getWorstSalesmanName()));

							System.out.println(
									String.format("Arquivo de saída gerado em: %s", outputFilePath.toString()));
						} catch (IOException ioException) {
							ioException.printStackTrace();
						}
					} catch (InvalidLineException registroInvalidoException) {
						registroInvalidoException.printStackTrace();
					} catch (InvalidFormatException formatoInvalidoException) {
						formatoInvalidoException.printStackTrace();
					}
				} else {
					System.out.println(String.format("Arquivo ignorado ao verificar extensão: %s.", filename));
				}
			}
			key.reset();
		}

	}
}

package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import entities.Sale;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.print("Entre o caminho do arquivo: ");
		String path = sc.nextLine();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			List<Sale> list = new ArrayList<>();
			List<Double> por = new ArrayList<>();

			String line = br.readLine();

			while (line != null) {
				String[] fields = line.split(",");
				list.add(new Sale(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]), fields[2],
						Integer.parseInt(fields[3]), Double.parseDouble(fields[4])));

				line = br.readLine();
			}

			Set<String> set = new HashSet<>();

			for (Sale nome : list) {
				set.add(nome.getSeller());
			}

			System.out.println();

			for (String i : set) {

				por = list.stream().filter(p -> p.getSeller().contains(i))
						.map(p -> p.getTotal())
						.collect(Collectors.toList());
				
				double soma = 0;				
				for (Double sum : por) {
					soma += sum;
				}
				System.out.println(i + " = " + String.format("%.2f", soma));

			}

		}

		catch (Exception e) {
			System.out.println("Erro: " + path + " (O sistema não pode encontrar o arquivo especificado)");
			System.out.println(e.getMessage());
		}

		sc.close();

	}

}

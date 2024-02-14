package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
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

			String line = br.readLine();

			while (line != null) {
				String[] fields = line.split(",");
				list.add(new Sale(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]), fields[2],
						Integer.parseInt(fields[3]), Double.parseDouble(fields[4])));
				line = br.readLine();

			}

			/*
			 * list.removeIf(p -> p.getYear() != 2016); 
			 * list.sort((p1,p2) -> (-p1.averagePrice().compareTo(p2.averagePrice())));
			 * 
			 * int x =0;
			 *  for(Sale p : list){ 
			 *   if(x < 5) { 
			 *   System.out.println(p + ", pm = "+ String.format("%.2f",p.averagePrice()));
			 *  x++;
			 *   } 
			 *  }
			 */
			/* list.forEach(System.out::println);*/ 
			
			System.out.println();			
			System.out.println("Cinco primeiras vendas de 2016 de maior preço médio");

			List<Sale> porDate = list.stream().filter(p -> p.getYear() == 2016)
					.sorted((p1,p2) -> (-p1.averagePrice().compareTo(p2.averagePrice())))
					.collect(Collectors.toList());

			int cont = 0;
			for (Sale je : porDate) {
				if(cont < 5) {			
				System.out.println(je + "pm = "+ String.format("%.2f",je.averagePrice()));
				cont++;
				}
			}
			
			
			System.out.println("\n");
		
			List<Sale> de = list.stream().filter(p -> p.getSeller().charAt(0) == 'L')
					.filter(x -> (x.getMonth() == 1) || (x.getMonth() == 7))
					.collect(Collectors.toList());
			

			double sum = 0;
			for (Sale ve : de) {

				sum+= ve.getTotal();
			}
			
			System.out.println("Valor total vendido pelo vendedor Logan nos meses 1 e 7 = "+String.format("%.2f", sum));

		} catch (Exception e) {
			System.out.println("Erro: " + path + " (O sistema não pode encontrar o arquivo especificado)");
			System.out.println(e.getMessage());
		}

		sc.close();

	}

}

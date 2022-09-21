package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

import entities.Sales;

public class Program {

	public static void main(String[] args) {
		
        Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		System.out.print("Entre o caminho do arquivo: ");
		String path = sc.nextLine();
		

		Map<String, Double> sales = new LinkedHashMap<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			
			String lines = br.readLine();
			
			 List <Sales> list = new ArrayList<>();
			
			while (lines != null) {
				String[] fields = lines.split(",");
				
				list.add(new Sales(Integer.parseInt(fields[0]),Integer.parseInt(fields[1]),fields[2],Integer.parseInt(fields[3]),Double.parseDouble(fields[4])));
	
				lines = br.readLine();
			}
			
			for (Sales li: list) {
				String name = li.getSeller();
				Double total = li.getTotal();
				
				if (sales.containsKey(name)) {
					double totalSoFar = sales.get(name);
					sales.put(name, total + totalSoFar);
				}
				else {
					sales.put(name, total );
				}
			}
			
			System.out.println();
			System.out.println("Total de vendas por vendedor:");
			
			for (String key : sales.keySet()) {
				System.out.println(key + " - R$ " + String.format("%.2f", sales.get(key)) );
			}

		} catch (IOException e) {
			System.out.println("Error:  " + e.getMessage());
		}
		finally {
			sc.close();
		}

	}

}

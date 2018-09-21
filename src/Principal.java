import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

public class Principal extends JFrame{

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("BNF para abrir o arquivo de txt padrão.");
		
		boolean abrirJanela = true;
		System.out.printf("digite o caminho do arquivo txt ou 'BNF': ");
	    String nome = scanner.nextLine();
	    if(nome.equals("BNF"))
	    	nome = "BNF.txt";
	    
	    BNF aux = new BNF();
	    ArrayList<BNF> bnf = new ArrayList<BNF>();
	    int x = 0;

	    try {
	    	FileReader fr = new FileReader(nome);
	    	BufferedReader br = new BufferedReader(fr);
	 
	    	String linha = br.readLine(); 
	    	
	    	for (int i = 0; i < linha.length(); i++) {
				if(linha.charAt(i) == '>'){
					aux.setNterminalId(linha.substring(0, i + 1));
				}else if(linha.substring(0, i).equals(aux.getNterminalId() + " ::= ")){
					x = i;
					break;
				}
			}
	    	
	    	for (int i = x; i < linha.length(); i++) {
	    		if(linha.charAt(i) == '|'){
	    			aux.getR().add(linha.substring(x, i));
	    			x = i + 1;
	    		}
			}
	    	
	    	aux.getR().add(linha.substring(x, linha.length()));
	    	bnf.add(aux);
	    	x = 0;
	    	
	    	while (linha != null) {
	    		System.out.printf("%s\n", linha);
	 
	    		linha = br.readLine();
	    		
	    		BNF aux2 = new BNF();
	    		
	    		if(linha != null){
	    			for (int i = 0; i < linha.length(); i++) {
	    				if(linha.charAt(i) == '>'){
	    					aux2.setNterminalId(linha.substring(0, i + 1));
	    				}else if(linha.substring(0, i).equals(aux2.getNterminalId() + " ::= ")){
	    					x = i;
	    					break;
	    				}
	    			}
		    	
	    			for (int i = x; i < linha.length(); i++) {
	    				if(linha.charAt(i) == '|'){
	    					aux2.getR().add(linha.substring(x, i));
	    					x = i + 1;
	    				}
	    			}
		    	
		    	aux2.getR().add(linha.substring(x, linha.length()));
		    	bnf.add(aux2);
		    	x = 0;
	    		}
	    		
		    	
	    	}
	 
	    	fr.close();
	    } catch (IOException e) {
	    	System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
	    	abrirJanela = false;
	    }
	 
	    System.out.println();
	    for (int i = 0; i < bnf.size(); i++) {
	    	System.out.println(bnf.get(i).getNterminalId());
			for (int j = 0; j < bnf.get(i).getR().size(); j++) {
				System.out.print("  " + bnf.get(i).getR().get(j));
			}
			System.out.println();
		}
	    if(abrirJanela){
	    	JFrame j = new JFrame("BNFtoDS");
	    	j.setSize(800,600);
	    	j.setLocationRelativeTo(null);
	    	j.setLayout(null);
		
	    	for (int i = 1; i <= bnf.size(); i++) {
	    		JButton a = new JButton(bnf.get(i - 1).getNterminalId());
	    		j.getContentPane().add(a);
	    		a.setBounds(350, i*50, 100, 30);
	    		View v = new View(bnf.get(i - 1), j);
	    		a.addActionListener(new ActionListener() {
				
	    			@Override
	    			public void actionPerformed(ActionEvent e) {
	    				j.dispose();
	    				v.setVisible(true);
	    				v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    			}
	    		});
	    	}
	    	j.setVisible(true);
	    	j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;
	    }
	    
	    
	}
	
}
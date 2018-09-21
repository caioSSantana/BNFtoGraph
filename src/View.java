import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

public class View extends JFrame{
	JFrame v;
	JButton volta;
	BNF bnf;
	int inicioX = 30;
	int inicioY = 80;
	private mxGraph graph;
	private mxGraphComponent component;
	
	public View(BNF bnf, JFrame v){
		super(bnf.getNterminalId());
		this.v = v;
		this.bnf = bnf;
		gerar();
	}
	
	public void gerar(){
		setSize(800,600);
		setLocationRelativeTo(null);
		graph = new mxGraph();
		component = new mxGraphComponent(graph);
		component.setPreferredSize(new Dimension(780, 500));
		getContentPane().add(component);
		
		Object parent = graph.getDefaultParent();
		Object vi = graph.insertVertex(parent, null, "[i]", 0, 120, 13, 13);
		
		ArrayList<Object> a = new ArrayList<Object>();
		ArrayList<Object> f = new ArrayList<Object>();
		
		graph.getModel().beginUpdate();
		Object aux2 = null;
		int x = inicioX;
		String aux = "";
		for (int i = 0; i < bnf.getR().size(); i++) {
			inicioX = 30;
			inicioY += 40;
			f.add(aux2);
			aux2 = vi;
			a.clear();
			for (int j = 0; j < bnf.getR().get(i).length(); j++) {
				//aux = aux + bnf.getR().get(i).charAt(j);
				if(bnf.getR().get(i).charAt(j) == '<'){
					//aux = aux + bnf.getR().get(i).charAt(j);
					while(bnf.getR().get(i).charAt(j) != '>'){
						aux = aux + bnf.getR().get(i).charAt(j);
						j++;
					}
					aux = aux + bnf.getR().get(i).charAt(j);
					
					a.add(graph.insertVertex(parent, null, aux, inicioX + 40, inicioY, 30, 13));
					graph.insertEdge(parent, null, "", aux2, a.get(a.size() - 1));
					aux2 = a.get(a.size() - 1);
					aux = "";
					inicioX += 50;
					x = inicioX;
				}else{
					aux = aux + bnf.getR().get(i).charAt(j);
					
					a.add(graph.insertVertex(parent, null, aux, inicioX + 40, inicioY, 30, 13));
					graph.insertEdge(parent, null, "", aux2, a.get(a.size() - 1));
					aux2 = a.get(a.size() - 1);
					aux = "";
					inicioX += 50;
					x = inicioX;
				}
				
				/*
				if(bnf.getR().get(i).charAt(j) == '>'){
					a.add(graph.insertVertex(parent, null, aux, inicioX + 40, inicioY, 30, 13));
					graph.insertEdge(parent, null, "", aux2, a.get(a.size() - 1));
					aux2 = a.get(a.size() - 1);
					aux = "";
					inicioX += 50;
					x = inicioX;
				}
				*/
			}
			
		}
		
		f.add(aux2);
		Object vf = graph.insertVertex(parent, null, "[f]", inicioX + x + 30, 120, 13, 13);
		
		for (int i = 0; i < f.size(); i++) {
			graph.insertEdge(parent, null, "", f.get(i), vf);
		}
		
		graph.getModel().endUpdate();
		
		setLayout(new FlowLayout());
		
		volta = new JButton("Voltar");
		getContentPane().add(volta);
		volta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				v.setVisible(true);
				v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
	}
	
}

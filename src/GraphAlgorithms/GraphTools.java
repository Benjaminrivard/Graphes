package GraphAlgorithms;
import java.util.*;
import java.io.*;

public class GraphTools {

	//private static int DEBBUG =1;
	private static int _DEBBUG =0;

	public GraphTools() {
	
	}

	/**
	 * 
	 * @param n, the number of vertices
	 * @param multi, at true if we want a multi-graph
	 * @param s, at true if the graph is symmetric
	 * @param c, at true if the graph is connected
	 * @param seed, the unique seed giving a unique random graph
	 * @return the generated matrix 
	 */ 
	public static int[][] generateGraphData(int n, boolean multi, boolean s, boolean c, int seed){
		if(_DEBBUG>0){
			System.out.println("\n ------------------------------------------------");
			System.out.println("<< Lancement de la méthode generateGraphData en aléatoire complet>>");
		}

		Random rand = new Random(seed);
		int m = (rand.nextInt(n)+1)*(n-1)/2;
		if(_DEBBUG>0){System.out.println("m = "+m);}
		int [][] Matrix = new int[n][n] ;	
		if(c){
			List<Integer> vis = new ArrayList<Integer>();
			int from = rand.nextInt(n);
			vis.add(from);
			from = rand.nextInt(n);
			while(vis.size()<n ){
				if(!vis.contains(from)){
					int indDest = rand.nextInt(vis.size());
					int dest = vis.get(indDest);				
					if(s)
						Matrix[dest][from] = 1;
					Matrix[from][dest] = 1;
					vis.add(from);
				}
				from = rand.nextInt(n);				
			}
			m -= n-1;
		}

		while(m>0){
			int i = rand.nextInt(n);
			int j = rand.nextInt(n);
			//int i = (int) (Math.rand()*n);
			//int j = (int) (Math.rand()*n);
			if(_DEBBUG>0){
				System.out.println("i = "+i);
				System.out.println("j = "+j);
			}
			if(!multi){
				if(i!=j && Matrix[i][j]!=1 ){
					if(s)
						Matrix[j][i] = 1;
					Matrix[i][j] = 1;
					m--;
				}
			}
			else{
				if(Matrix[i][j]==0 ){
					int val = ( i!=j ? ( m<3 ? m: (int) (rand.nextInt(3))+1) : 1);
					//int val = ( i!=j ? ( m<3 ? m: (int) (Math.random()*3)+1) : 1);
					if(_DEBBUG>0){
						System.out.println("Pour multi, val = "+val);
					}
					if(s)
						Matrix[j][i] = val;
					Matrix[i][j] = val;
					m -= val;
				}
			}
		}
		return Matrix;
	}

	/**
	 * 
	 * @param n, the number of vertices
	 * @param m, the number of edges
	 * @param multi, at true if we want a multi-graph
	 * @param s, at true if the graph is symmetric
	 * @param c, at true if the graph is connexted
	 * @param seed, the unique seed giving a unique random graph
	 * @return the generated matrix
	 */ 
	public static int[][] generateGraphData(int n, int m, boolean multi, boolean s, boolean c, int seed){
		if(_DEBBUG>0){
			System.out.println("\n ------------------------------------------------");
			System.out.println("<< Lancement de la méthode generateGraphData >>");
		}
		int [][] Matrix = new int[n][n] ;
		Random rand = new Random(seed);
		if(c){
			List<Integer> vis = new ArrayList<Integer>();
			int from = rand.nextInt(n);
			vis.add(from);
			from = rand.nextInt(n);
			while(vis.size()<n ){
				if(!vis.contains(from)){
					int indDest = rand.nextInt(vis.size());
					int dest = vis.get(indDest);				
					if(s)
						Matrix[dest][from] = 1;
					Matrix[from][dest] = 1;
					vis.add(from);
				}
				from = rand.nextInt(n);				
			}
			m -= n-1;
		}

		while(m>0){
			int i = rand.nextInt(n);
			int j = rand.nextInt(n);
			//int i = (int) (Math.rand()*n);
			//int j = (int) (Math.rand()*n);
			if(_DEBBUG>0){
				System.out.println("i = "+i);
				System.out.println("j = "+j);
			}
			if(!multi){
				if(i!=j && Matrix[i][j]!=1 ){
					if(s)
						Matrix[j][i] = 1;
					Matrix[i][j] = 1;
					m--;
				}
			}
			else{
				if(Matrix[i][j]==0 ){
					int val = ( i!=j ? ( m<3 ? m: (int) (rand.nextInt(3))+1) : 1);
					//int val = ( i!=j ? ( m<3 ? m: (int) (Math.random()*3)+1) : 1);
					if(_DEBBUG>0){
						System.out.println("Pour multi, val = "+val);
					}
					if(s)
						Matrix[j][i] = val;
					Matrix[i][j] = val;
					m -= val;
				}
			}
		}
		return Matrix;
	}

	/**
	 * 
	 * @param n, the number of vertices
	 * @param multi, at true if we want a multi-graph
	 * @param s, at true if the graph is symmetric
	 * @param c, at true if the graph is connexted
	 * @param neg, at true if the graph has negative weights 
	 * @param seed, the unique seed giving a unique random graph
	 * @return
	 */
	public static int[][] generateValuedGraphData(int n, boolean multi, boolean s, boolean c, boolean neg, int seed){
		if(_DEBBUG>0){
			System.out.println("\n ------------------------------------------------");
			System.out.println("<< Lancement de la méthode generateValuedGraphData >>");
		}

		int[][] mat = generateGraphData(n, multi, s, c, seed);
		int [][] matValued = new int[mat.length][mat.length] ;
		Random rand = new Random(seed);
		int valNeg =0;
		if(neg)
			valNeg = -6;

		for(int i =0; i<mat.length; i++){
			for(int j =0; j<mat[0].length; j++){
				if(mat[i][j]>0){
					int val = ((int) (rand.nextInt(15)))+1+valNeg;
					//int val = ((int) (Math.random()*15))+1+valNeg;
					matValued[i][j] = val;
					if(s)
						matValued[j][i] = val;
				}
			}
		}

		return matValued;
	}

	/**
	 * @param M, a matrix
	 */
	public static void AfficherMatrix(int[][] M){
		for(int i =0;i<M.length;i++){
			for(int j =0;j<M[i].length;j++){
				System.out.print(M[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}


	/**
	 * @param mat, a matrix
	 * @return the symmetrical matrix 
	 */
	public static int[][] MatrixSym(int[][] mat){
		for(int i =0;i<mat.length;i++){
			for(int j =0;j<mat[i].length;j++){
				if(mat[i][j]==1)
					mat[j][i]=1;
			}
		}
		return mat;
	}


	
	public static void main(String[] args) {
		int[][] mat = generateGraphData(10, 20, false, false, false, 100001);
		AfficherMatrix(mat);
		int[][] mat2 = generateGraphData(10, 20, false, false, false, 100002);
		AfficherMatrix(mat2);
		int[][] mat3 = generateGraphData(10, 20, false, true, true, 100003);
		AfficherMatrix(mat3);
		int[][] matVal = generateValuedGraphData(10, false, false, true, true, 100007);
		AfficherMatrix(matVal);

		//mat = generateGraphData(10, false, false, true, 4323);
		//AfficherMatrix(mat);
		//MatrixSym(M);
		//AfficherMatrix(M);
	}

}

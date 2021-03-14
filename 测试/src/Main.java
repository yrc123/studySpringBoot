import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		final Scanner scanner = new Scanner(System.in);
		final ArrayList[] arr = new ArrayList[1001];
		final Queue<Integer> queue = new Queue<Integer>;
		int vis[]=new int[1001];
		int cost[][]=new int[1001][1001];
		for(int i=0;i<1001;i++){
			arr[i]=new ArrayList<Integer>();
			for(int j=0;j<1001;j++){
				if(i==j)
					cost[i][j]=0;
				else
					cost[i][j]=-1;
			}
		}

		int n,m;
		n=scanner.nextInt();
		m=scanner.nextInt();
		for(int i=0;i<m;i++){
			int a,b;
			a=scanner.nextInt();
			b=scanner.nextInt();
			arr[a].add(b);
		}
		for(int i=1;i<=n;i++){
			int len = arr[i].size();
			for(int j=0;j<len;j++){
				int j
			}
		}
	}
}

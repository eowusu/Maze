import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;


public class PathFinder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] pathMap = new int[][]{
				{0,    1,    1,    0,    1,    1,    1,    1,    0,    1},
			    {0,    1,    0,    0,    0,    0,    1,    1,    1,    1},
			    {0,    1,    0,    0,    1,    0,    1,    1,    1,    1},
			    {0,    0,    0,    1,    1,    0,    1,    1,    1,    1},
			    {0,    1,    1,    1,    0,    0,    1,    0,    0,    1},
			    {1,    1,    1,    1,    0,    1,    1,    0,    0,    1},
			    {1,    1,    0,    1,    0,    0,    0,    0,    0,    1},
			    {1,    1,    0,    0,    1,    1,    1,    1,    0,    1},
			    {0,    1,    1,    0,    1,    1,    1,    1,    0,    1},
			    {0,    1,    1,    1,    1,    1,    1,    1,    0,    0}
				};
		findPath(pathMap, 0,0, 9,9);
			}
	
	public static void printArray(int[][] array){
		for(int y = 0; y < array.length; y++){
			for(int x = 0; x <array[0].length; x++){
				if(array[y][x]<Integer.MAX_VALUE-2)
					System.out.print(array[y][x]+ " ");
				else System.out.print("* ");
			}
			System.out.println("");
		}
	}
	public static void findPath(int inmap[][], int xi, int yi, int xf, int yf){
		int[][] myMap = new int[inmap.length+2][inmap[0].length+2];
		int[][] visited = new int[myMap.length][myMap.length];
		for(int y = 0; y < inmap.length+2; y++){
			for(int x = 0; x < inmap[0].length+2; x++){
				visited[y][x] = 0;
				if(y == 0 || y >= inmap.length+1 || x == 0 || x >= inmap[0].length+1){
					myMap[y][x] = Integer.MAX_VALUE-1;
					continue;
				}
				else if(inmap[y-1][x-1] == 1)
					myMap[y][x] = Integer.MAX_VALUE -1;
				else
					myMap[y][x] = Integer.MAX_VALUE - 2;
			}
		}
		myMap[1][1] = 0;

		Pos initial = new Pos(1,1);
		ArrayDeque<Pos> q = new ArrayDeque<Pos>();
		q.addLast(initial);
		while(!q.isEmpty()){
			Pos current = q.removeFirst();
			int x = current.x;
			int y = current.y;
			if(myMap[y][x] < Integer.MAX_VALUE - 1 && x >= 0 && y >= 0 && visited[y][x] == 0){
				myMap[y][x] = Integer.min(myMap[y][x], Integer.min(Integer.min(myMap[y+1][x], myMap[y-1][x]), Integer.min(myMap[y][x+1], myMap[y][x-1])) + 1);
				if(visited[y][x-1] == 0)
					q.addLast(new Pos(y, x-1));
				if(visited[y][x+1] == 0)
					q.addLast(new Pos(y, x +1));
				if(visited[y+1][x] == 0)
					q.addLast(new Pos(y+1, x));
				if(visited[y-1][x] == 0)
					q.addLast(new Pos(y-1, x));
				visited[y][x] = 1;
			}

		}

		printArray(myMap);
		
		printPath(xi+1,yi+1, xf+1, yf+1, myMap);
}

	public static void printPath(int xi, int yi, int xf, int yf, int[][] myMap){
		System.out.println("Final position: "+ (xf-1) +","+(yf-1));
		while(xf != xi && yf != yi){
			Pos[] neighbours = {new Pos(yf-1, xf), new Pos(yf+1, xf), new Pos(yf, xf-1), new Pos(yf, xf+1)};
			int[] vals = new int[4];
			for(int i = 0 ; i < 4; i++){
				vals[i] = myMap[neighbours[i].y][neighbours[i].x];
			}
			for(int i = 0; i < 4; i++){
				if(vals[i] == Integer.min(Integer.min(vals[0], vals[1]), Integer.min(vals[2], vals[3]))){
					xf = neighbours[i].x;
					yf = neighbours[i].y;
				}
			}
			System.out.print("("+(xf-1)+", "+(yf-1)+")  ");
		}
	}

}

class Pos{
	public int x;
	public int y;
	public Pos(int yin, int xin){
		x = xin;
		y = yin;
	}
}

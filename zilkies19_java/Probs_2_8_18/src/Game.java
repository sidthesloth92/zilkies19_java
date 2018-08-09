import java.util.*;

public class Game {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter size of game board :  ");
		int n = scan.nextInt();
		int[] arr = new int[n];
		System.out.println("---  Enter board values  ---");
		for (int i = 0; i < n; i++)
			arr[i] = scan.nextInt();
		System.out.print("Enter leap size :  ");
		int leap = scan.nextInt();
		boolean[] visited = new boolean[n];
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(0);
		visited[0] = true;
		boolean flag = false;
		while (!flag && !queue.isEmpty()) {
			int pos = queue.poll();
			if (pos + 1 >= n || pos + leap >= n) {
				flag = true;
				break;
			}
			if (pos - 1 >= 0 && !visited[pos - 1] && arr[pos - 1] != 1) {
				queue.add(pos - 1);
				visited[pos - 1] = true;
			}
			if (pos + 1 < n && !visited[pos + 1] && arr[pos + 1] != 1) {
				queue.add(pos + 1);
				visited[pos + 1] = true;
			}
			if (pos + leap < n && !visited[pos + leap] && arr[pos + leap] != 1) {
				queue.add(pos + leap);
				visited[pos + leap] = true;
			}
		}
		if (flag)
			System.out.println("OUT");
		else
			System.out.println("NOT OUT");
	}
}

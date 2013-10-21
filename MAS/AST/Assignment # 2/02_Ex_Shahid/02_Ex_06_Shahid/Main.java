
public class Main
{
	public static void main(String[] arguments)
	{
		BrickLayer brickLayer = new BrickLayer(
				new int[] { 7, 7, 7, 4, 4, 6, 2, 8, 6, 2 }, 
				new int[] { 3, 7, 4, 6, 6, 2, 7, 5, 8, 5 });
		brickLayer.layBricks();
	}
}


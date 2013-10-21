/**
 * @author Muneeb Shahid
 */
public class BrickLayer
{
	/**
	 * Black brick on the floor is represented by 1
	 * And White brick by 0
	 */
	private final int blackBrick = 1;
	/**
	 * 2D grid representation of the floor
	 */
	private int[][] floor;
	
	/**
	 * Contains max number of bricks allowed in each column
	 */
	private int[] blackBricksInColumn;
	
	/**
	 * Contains max number of bricks allowed in each row
	 */
	private int[] blackBricksInRow;

	public BrickLayer(int[] blackBricksInRow, int[] blackBricksInColumn)
	{
		this.floor = new int[10][10];
		this.blackBricksInRow = blackBricksInRow;
		this.blackBricksInColumn = blackBricksInColumn; 
	}

	/**
	 * Returns the number of bricks allowed for the provided row
	 * @param row
	 * @return
	 */
	public int noOfBlackBricksInRow(int row)
	{
		return this.blackBricksInRow[row];
	}

	/**
	 * Retursn the number of bricks allowed for the provided column
	 * @param column
	 * @return
	 */
	public int noOfBlacBricksInColumn(int column)
	{
		return this.blackBricksInColumn[column];
	}

	/**
	 * Finds the column first black brick in the previous row. So we may start putting black
	 * bricks in the current row from that column.
	 * @param row
	 * @return
	 */
	public int findFirstBlackBrickInPreviousRow(int row)
	{
		if (row == 0)
			return -1;
		for (int i = 0; i < this.floor.length; i++)
		{
			if (this.floor[row - 1][i] == this.blackBrick)
			{
				return i;
			}
		}
		return 0;
	}

	/**
	 * Checks if a black brick can be placed a the given destination
	 * @param row
	 * @param column
	 * @return
	 */
	public boolean checkIfPlaceAble(int row, int column)
	{
		boolean placeAble = true;
		int blackBricks = 0;
		for (int i = 0; i < this.floor.length; i++)
		{
			blackBricks = blackBricks + this.floor[row][i];
			if (blackBricks > this.noOfBlackBricksInRow(row))
			{
				placeAble = false;
				break;
			}
		}
		blackBricks = 0;
		for (int i = 0; i < this.floor.length; i++)
		{
			blackBricks = blackBricks + this.floor[i][column];
			if (blackBricks > this.noOfBlacBricksInColumn(column))
			{
				placeAble = false;
				break;
			}
		}
		return placeAble;
	}

	/**
	 * Entry point for the class.
	 */
	public void layBricks()
	{
		for (int row = 0; row < floor.length; row++)
		{
			for (int column = 0; column < floor.length; column++)
			{
				if (this.floor[row][column] != this.blackBrick)
				{
					if (this.checkIfPlaceAble(row, column))
					{
						this.findFirstBlackBrickInPreviousRow(row);
					}
				}
			}
		}
	}
}

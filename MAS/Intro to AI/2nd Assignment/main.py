__author__ = 'muneeb'
from world import self
from bfs import BFS
from util import Util

world = self(Util.read_file("maps/map1.txt"))
bfs_search = BFS(world)
bfs_search.search()

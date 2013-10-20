__author__ = 'muneeb'
from world import World
from bfs import BFS
from util import Util

world = World(Util.read_file("maps/map1.txt"))
bfs_search = BFS(world)
bfs_search.search()

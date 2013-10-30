__author__ = 'muneeb'
from world import World
from bfs import BFS
from dfs import DFS
from util import Util
import timeit

map1 = "maps/map1.txt"
map2 = "maps/map2.txt"
map3 = "maps/map3.txt"

b_map1 = "generatedpaths/bfs/map1.txt"
b_map2 = "generatedpaths/bfs/map2.txt"
b_map3 = "generatedpaths/bfs/map3.txt"

d_map1 = "generatedpaths/dfs/map1.txt"
d_map2 = "generatedpaths/dfs/map2.txt"
d_map3 = "generatedpaths/dfs/map3.txt"

print "\nBFS - Map1"
world = World(Util.read_file(map1), b_map1)
bfs_search = BFS(world)
print timeit.Timer(bfs_search.search).timeit(1)

print "\nBFS - Map2"
world = World(Util.read_file(map2), b_map2)
bfs_search = BFS(world)
bfs_search.search()
print timeit.Timer(bfs_search.search).timeit(1)

print "\nBFS - Map3"
world = World(Util.read_file(map3), b_map3)
bfs_search = BFS(world)
bfs_search.search()
print timeit.Timer(bfs_search.search).timeit(1)

print "\nDFS - Map1"
world = World(Util.read_file(map1), d_map1)
dfs_search = DFS(world)
dfs_search.search()
print timeit.Timer(dfs_search.search).timeit(1)

print "\nDFS - Map2"
world = World(Util.read_file(map2), d_map2)
dfs_search = DFS(world)
dfs_search.search()
print timeit.Timer(dfs_search.search).timeit(1)

print "\nDFS - Map3"
world = World(Util.read_file(map3), d_map3)
dfs_search = DFS(world)
dfs_search.search()
print timeit.Timer(dfs_search.search).timeit(1)

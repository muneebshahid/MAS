__author__ = 'muneeb'

from node import Node
from ids import IDS
from dfs import DFS
from bfs import BFS
from graph_generator import  Graph_Generator
import copy
start = "1"
goal = "15"
g = Graph_Generator(8000)
graph = g.generate_graph()

ids = IDS(copy.deepcopy(graph), start, goal)
bfs = BFS(copy.deepcopy(graph), start, goal)
dfs = DFS(copy.deepcopy(graph), start, goal)

ids.walk()
bfs.walk()
dfs.walk()


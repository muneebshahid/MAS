__author__ = 'muneeb'
import sys


class DFS():
    __world = None

    def __init__(self, world):
        self.__world = world
        sys.setrecursionlimit(10000)
        """:type : World"""

    def search(self):
        self.__search(self.__world.get_start())

    def __search(self, node):
        self.__world.add_to_explored(node)
        """:type : Node"""
        if node.get_data() == self.__world.dirt():
            print "Dirt cleaned at node " + node.get_node_key()
        self.__world.update_world(node)
        for adjacent_node in self.__world.get_adjacent_nodes(node):
            if self.__world.get_is_explored(adjacent_node): continue
            self.__search(adjacent_node)
        return
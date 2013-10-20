__author__ = 'muneeb'
from world import World
from node import Node


class BFS():
    __world = None

    def __init__(self, world):
        self.__world = world
        self.__world.add_to_fringe(self.__world.get_start())
        """:type : World"""

    def search(self):
        return self.__search()

    def __search(self):
        while self.__world.fringe_len() > 0:
            node = self.__world.pop_from_fringe()
            """:type : Node"""
            self.__world.update_world(node)
            if node.get_data() == self.__world.dirt():
                print "Dirt cleaned at node " + node.get_node_key()
            for adjacent_node in self.__world.get_adjacent_nodes(node):
                if self.__world.get_is_explored(adjacent_node): continue
                self.__world.add_to_fringe(adjacent_node)
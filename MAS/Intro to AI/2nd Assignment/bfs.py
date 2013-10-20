__author__ = 'muneeb'
from world import World


class BFS():
    __fringe = []
    __world = None

    def __init__(self, world):
        self.__world = world
        """:type : World"""
        self.__fringe.append(self.__world.get_start())

    def search(self):
        while len(self.__fringe) > 0:
            node_key = self.__fringe.pop(0)
            if self.__world.get_node_data(node_key) == self.__world.dirt():
                print "Dirt found at node" + node_key + self.__world.get_node_data(node_key)
            for adjacent_node_key in self.__world.get_adjacent_nodes(node_key):
                if self.__world.get_is_explored(adjacent_node_key):
                    continue
                self.__world.set_is_explored(adjacent_node_key, True)
                self.__fringe.append(adjacent_node_key)
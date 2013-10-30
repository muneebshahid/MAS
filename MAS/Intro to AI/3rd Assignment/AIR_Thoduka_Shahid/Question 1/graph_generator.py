__author__ = 'muneeb'
from node import Node


class Graph_Generator():
    __last_digit = None
    __start = "1"
    __graph = None
    __branching_factor = None

    def __init__(self, last_digit):
        self.__last_digit = last_digit
        self.__graph = {}
        self.__branching_factor = 2

    def get_element_row_no(self, element):
        sum_no = 0
        exp = 0
        while sum_no < element:
            sum_no += pow(self.__branching_factor, exp)
            exp += 1
        return exp

    def generate_graph(self):
        upper_range = self.__last_digit + 2
        cut_off = self.get_element_row_no(self.__last_digit)
        for i in range(1, upper_range):
            node_1 = i * 2
            node_2 = node_1 + 1
            self.__graph[str(i)] = self.get_element_row_no(i) < cut_off and Node([str(node_1), str(node_2)]) or Node([])
        return  self.__graph


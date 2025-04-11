import networkx as nx
import matplotlib.pyplot as plt
import ast

# Raw graph data as a string
raw_data = ""

# Replace '=' with ':' to make it a valid Python dictionary
formatted_data = raw_data.replace('=', ':')

# Convert string to actual dictionary
graph_data = ast.literal_eval(formatted_data)

# Create a graph object
G = nx.Graph()

# Add edges to the graph
for node, neighbors in graph_data.items():
    for neighbor in neighbors:
        G.add_edge(node, neighbor)

# Draw the graph
plt.figure(figsize=(15, 10))
pos = nx.spring_layout(G, seed=42)  # Positioning of nodes
nx.draw(G, pos, with_labels=True, node_color='lightblue', edge_color='gray', node_size=5, font_size=10)
plt.title("Graph Visualization")
plt.show()

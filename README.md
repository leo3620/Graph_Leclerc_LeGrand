# Graph-Framework

This repository is used during the practice classes of "Graphe et algorithmes" course in IMT Atlantique.

This is a standard maven project so please use the according import option in your IDE (IntelliJ IDEA or Eclipse).

-- 

Ce projet à été réalisé par Léo LECLERC et Alan Le Grand étudiant en FIL A2

En ce qui concerne le TP1 :   

Les méthodes demandés ont été complétées, ainsi les classes UndirectedGraph, DirectedGraph, AdjacencyMatrixDirectedValuedGraph, AdjacencyMatrixUndirectedValuedGraph ... ont été complétées.

TP2:  

L'algorithme de parcours en largeur est realisés dans la classe graphToolsList via la méthode breadthFirstSearch. Celle ci prend un graph sous forme de liste et affiche la liste des noeuds au cours du parcours.
Pour réaliser cette methode de facon génerique, nous avons ajoutée la methode getSuccOrNeighbour() dans AbstractNode qui donne soit la liste des successeurs soit la liste des voisins.  


En ce qui concerne la question 22, les methodes explorerGraph et explorerSommet ont été réalisé de facon independante des autres questions afin de specifier le besoin de cette methode. La trace obtenue correspond à une liste de graph connexes

TP4: 



Par ailleurs les algorithmes de Bellman et Djikstra ont egalement été réalisés dans la clase GraphToolList:  

* Pour Djikstra, la méthode appelé est djikstra() et prend 4 variables en paramètres :  
      1 - Le Graph sous forme matricielle  
      2 - Le nombre de noeuds  
      3 - Le noeud de départ  
      4 - Le noeud d'arrivé  
Lorsque la méthode est appellée, deux traces apparaissent :  
 1 - Le tableau V correspondant aux valeurs des noeuds  
 2 - Le chemin permettant de rejoindre le noeuds de destination si c'est possible
 
 A noter, dans la representation matricielle si il n'existe pas de chemin entre deux noeuds cela est representé par 0, des lors la methode est un peu biaisée, 
 Pour cela nous avons pris le parti qu'il n'est pas possible d'avoir un chemin avec pour valeur 0. 
 0 representant donc uniquement l'absence de chemin et non une valuation.  
 
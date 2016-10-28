# Star Trek Rescue

A Frota Estelar recebeu um pedido de resgate de uma tripulação perdida em um planeta desabitado.

Porém, devido a nave ter sido muito danificada na queda, a tripulação de 3 pesquisadores se separou e somente conseguiu enviar parte da informação sobre a sua localização.

Com base nessas informações, a Frota Estelar conseguiu identificar que os tripulantes estavam separados aleatoriamente em 3 pontos de uma planície de 10x10 ao Norte do planeta.

A USS Enterprise tem que marcar a posição dos usuários lançando sinalizadores nas possíveis posições onde os tripulantes estejam, para que as equipes de busca os encontrem. 

Você, como parte da equipe de engenharia da USS Enterprise, tem que desenvolver esse sistema que, mapeando a planície em formato de malha, dispare sinalizadores para pontos da planície (usando como referência pontos dos eixos x, y), um sinalizador indicando a posição. Os sinalizadores devem ser lançados um de cada vez, aguardando o retorno do anterior lançado.

O sinalizador ao chegar no ponto informado, deve informar se localizou algum
tripulante (Usando o X ).

Caso não tenha encontrado, ele rastreará as áreas adjacentes ao ponto que ele chegou, o alcance é de uma área, informando se em alguma delas existe um tripulante (marcando com um ! ). 

Observe que há uma imprecisão no sinalizador, então ele marca as áreas adjacentes em comum aos dos dois pontos, sinalizador e tripulante.
	Se não encontrar nada naquele ponto e nas respectivas áreas adjacentes marque com * para mapear a malha com mais precisão.

# Desenvolvimento

Para o desenvolvimento da proposta, foi utilizado Java 7 com abordagem TDD e as seguintes tecnologias:
-	**JUnit** para os testes
-	**JavaFX** para interface gráfica

#	Testes automatizados
As seguintes classes realizam os testes automatizados:
-	**RescueTest.java:** Testa a execução do projeto com jogadas aleatórias até que todos os tripulantes sejam encontrados
-	**TestaCriacaoDeTripulantes.java:** Verifica se a criação da lista de tripulantes está correta


# Modo de execução

Foram geradas as classes **MainUI.java** e **MainConsole.java** para execução do projeto.

A primeira fornece ao usuário uma interface gráfica para que o mesmo possa entrar com seus dados  e realizar buscas pelos tripulantes.

No segundo modo, a execução será realizada pelo Console do Eclipse.





  


# Seminarios Cientificos

Projeto em JUnit5


# Requisitos do Sistema de Seminários Científicos
O sistema de seminários científicos possui como premissa básica a compra e o check-in de inscrições para vários seminários por um estudante. Para cumprir essa premissa, a primeira funcionalidade do sistema deverá ser o cadastro de instituições por um administrador. Este cadastro deve ser composto pelas seguintes informações: nome, Cidade, Estado, País e Sigla da instituição.

Para continuar o cumprimento da premissa básica, o sistema deve permitir o cadastro de áreas cientificas (nome) e então possibilitar o cadastro de informações relacionadas aos cursos que pertençam a uma área cientifica. Um curso é composto por um nome e uma área cientifica.

No sistema, todo professor deve estar associado com uma instituição. Por outro lado, uma instituição pode possuir diversos professores. O cadastro de um professor exige as informações de nome, e-mail, telefone, salário.

Após a criação das áreas cientificas e do cadastro dos professores no sistema, é possível realizar a criação de Seminários. Esses necessitam ter ao menos 1 professor e pertencer a pelo menos uma área cientifica. Outras informações para um seminário são: a data de realização, título, descrição, informativo sobre mesas redondas. Ademais, cada seminário deve possuir uma quantidade de inscrições disponíveis. Por fim, um último aspecto relacionado ao seminário é que ao criá-lo, o sistema deverá gerar as inscrições para sua comercialização.

Neste momento é importante ressaltar que cada inscrição possui um estado relacionado à sua comercialização, que pode ser: Disponível, Comprado ou CheckIn. O estado Disponível representa que a inscrição ainda não foi adquirida por algum estudante. O estado Comprado representa que a inscrição já foi adquirida por um estudante e o estado CheckIn é que o estudante que adquiriu a inscrição e já está participando do seminário, tendo comparecido ao evento.

Conforme o comportamento esperado, um estudante pode realizar inscrição para qualquer seminário, independente da área cientifica que este possui, e deve possuir as seguintes informações de cadastro: nome, e-mail, telefone.

Por fim, ao adquirir uma inscrição é permitido ao estudante comprar o material do seminário.

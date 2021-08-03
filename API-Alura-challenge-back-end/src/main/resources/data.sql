INSERT INTO `perfil` (`id`, `nome`) VALUES 
(NULL, 'PADRAO'), 
(NULL, 'GESTOR');

INSERT INTO `usuario` (`id`, `email`, `senha`) VALUES 
(NULL, 'teste@gmail.com', '$2a$10$4gYmDdHsJmrTbBlW8y9.O.EbOUmhtk5QzTwX.ovS//EP64XutdZJm'), 
(NULL, 'challengebackend@alura.com', '$2a$10$4gYmDdHsJmrTbBlW8y9.O.EbOUmhtk5QzTwX.ovS//EP64XutdZJm');

INSERT INTO `perfil_usuarios` (`perfil_id`, `usuarios_id`) VALUES 
('1', '2'), 
('1', '1');

INSERT INTO `categoria` (`id`, `cor`, `titulo`) VALUES
(NULL, '#FFFFFF', 'LIVRE'),
(NULL, '#FFFF00', 'PROGRAMACAO');

INSERT INTO `video` (`id`, `descricao`, `titulo`, `url`, `categoria_id`) VALUES 
(NULL, 'O mercado de tecnologia tem crescido bastante, diversas profissões foram surgindo e com elas várias dúvidas. É melhor ser especialista ou generalista? Como se tornar o(a) profissional que as empresas estão procurando? Paulo Silveira responde!', 'Carreiras Hipster em Tecnologia', 'https://www.youtube.com/watch?v=AG8cYHCbGcg&t=34s&ab_channel=AluraCursosOnline', 2), 
(NULL, 'Será que é mesmo tão complicado aprender programação? Preciso de faculdade para estar na área? Neste vídeo, Vanessa Tonini e Gabriel Ferreira falam sobre estas e mais algumas questões relacionadas a programação que não são tão verdades assim (ou nem são)... Confira!', '4 mitos sobre programação', 'https://www.youtube.com/watch?v=yj0FR_gm0sM&t=40s&ab_channel=AluraCursosOnline', 2),
(NULL, 'Você já trabalhou ou trabalha em casa? A Roberta Arcoverde  é uma das pessoas que trabalha neste formato e contou pra gente neste vídeo sobre sua rotina. E você, também trabalha de casa? Conte sobre sua experiência nos comentários! ', 'Como é o trabalho remoto?', 'https://www.youtube.com/watch?v=V0FuAukFSzc&ab_channel=AluraCursosOnline', 2),
(NULL, 'O que é Front-end? Trabalhando na área os termos HTML, CSS e JavaScript fazem parte da rotina das desenvolvedoras e desenvolvedores. Mas o que eles fazem, afinal? Descubra com a Vanessa!', 'O que faz uma desenvolvedora front-end?', 'https://www.youtube.com/watch?v=ZY3-MFxVdEw&ab_channel=AluraCursosOnline', 2),
(NULL, 'Domain Driven Design, o que é? Por que foi criada e qual objetivo dessa linguagem dentro da programação?\r\n\r\nO @Dev Eficiente explicou como funciona o Domain Driven Design e como aplicar essa abordagem de forma estratégica e tática para entregar um software de alta qualidade. Confere aí 😉', 'Domain Driven Design com Alberto Sousa, o Dev Eficiente', 'https://www.youtube.com/watch?v=n40Z1c9Ryog&ab_channel=AluraCursosOnline', 2),
(NULL, 'Neste episódio do Hipsters.tube, Mario Souto e Paulo Silveira conversam sobre o Node .JS, eles explicam o que essa plataforma tão utilizada no universo Front-end faz, como ela surgiu e quais são suas aplicações.', 'O que é Node JS?', 'https://www.youtube.com/watch?v=GKR6uSvEj8w&ab_channel=AluraCursosOnline', 1),
(NULL, 'Por que emuladores e vídeo games antigos são importantes na programação?\r\n\r\nConvidamos o @Fabio Akita  e ele explicou como funcionam os emuladores e a importância de dominar esse software e entender códigos-fontes.', 'Como Funcionam Os Emuladores com Fábio Akita', 'https://www.youtube.com/watch?v=9qx7qjKhJ1Q&t=42s&ab_channel=AluraCursosOnline', 1);
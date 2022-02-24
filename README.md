# Spring Cloud Vault

- O Vault é um sistema de gerenciamento de criptografia e segredos baseados em identidade. Um segredo é tudo o que você deseja controlar rigidamente o acesso, como chaves de criptografia de API, senhas ou certificados. O Vault fornece serviços de criptografia que são protegidos por métodos de autenticação e autorização. Usando a interface do usuário, CLI ou API HTTP do Vault, o acesso a segredos e outros dados confidenciais pode ser armazenado e gerenciado com segurança, rigidamente controlado (restrito) e auditável.

- Um sistema moderno requer acesso a uma infinidade de segredos: credenciais de banco de dados, chaves de API para serviços externos, credenciais para comunicação de arquitetura orientada a serviços, etc. Entender quem está acessando quais segredos já é muito difícil e específico da plataforma. Adicionar rolagem de chaves, armazenamento seguro e logs de auditoria detalhados é quase impossível sem uma solução personalizada. É aqui que o Vault entra em cena.

- No exemplo deste repositorio, utilizamos o spring vault, aonde:
  - a aplicação não conhece as credenciais reais do banco de dados, apenas as do vault
  - o vault concederá uma credencial a aplicação com duração de 10 min, após esse tempo, caso não renove, será inválida
  - nesse processo, a aplicação se autentica no vault (via service account do kubernetes), pega as credenciais e com elas consegui se conectar na base de dados
  - existe um plugin dentro do postgres que cuida do mecanismo de validar a credencial do vault passada no próprio vault
  - a cada 30 segundos a aplicação renovará a credencial, afim de não tomar um erro de conexão com a base de dados

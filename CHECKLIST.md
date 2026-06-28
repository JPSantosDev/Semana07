# Checklist de Testes – Aprender+ Acesso

## Abertura

- [ ] O app abre no emulador sem crash
- [ ] A tela inicial exibe o título **"Aprender+ Acesso"**
- [ ] O modo login aparece por padrão

## Modo Login

- [ ] Clicar em **"Entrar"** com campos vazios mostra erros
- [ ] E-mail sem `@` mostra erro correto
- [ ] E-mail sem ponto no domínio mostra erro correto
- [ ] Senha com menos de 6 caracteres mostra erro
- [ ] Senha válida remove o erro
- [ ] Login válido exibe snackbar **"Login validado com sucesso"**
- [ ] Login inválido exibe snackbar de erro
- [ ] Checkbox **"Manter-me conectado"** marca e desmarca corretamente

## Modo Cadastro

- [ ] Clicar em **"Criar conta"** troca para o modo cadastro
- [ ] Nome vazio mostra erro
- [ ] Nome com menos de 10 caracteres mostra erro
- [ ] Nome sem sobrenome mostra erro
- [ ] E-mail inválido mostra erro
- [ ] Senha com menos de 6 caracteres mostra erro
- [ ] Senha sem número mostra erro
- [ ] Confirmação diferente da senha mostra erro
- [ ] Termos desmarcados bloqueiam o cadastro
- [ ] Cadastro válido exibe snackbar **"Cadastro validado com sucesso"**
- [ ] Cadastro inválido exibe snackbar de erro
- [ ] Clicar em **"Já tenho conta"** retorna para o modo login

## Senha

- [ ] Senha inicia oculta
- [ ] Clicar no ícone exibe a senha
- [ ] Clicar novamente oculta a senha
- [ ] Campo de confirmação de senha também alterna visibilidade
- [ ] O valor digitado não é alterado ao alternar visibilidade

## Dialog de Cancelamento

- [ ] Clicar em **"Cancelar"** abre o dialog
- [ ] Dialog exibe título **"Descartar dados?"**
- [ ] Dialog exibe texto **"Os dados preenchidos serão perdidos."**
- [ ] Clicar em **"Voltar"** fecha o dialog sem limpar os campos
- [ ] Clicar em **"Descartar"** limpa todos os campos
- [ ] Após descartar, checkbox de termos volta para desmarcado

## Estabilidade

- [ ] O app não fecha inesperadamente em nenhum cenário testado
- [ ] Alternar entre login e cadastro não perde o estado inesperadamente

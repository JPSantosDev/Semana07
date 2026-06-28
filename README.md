# Aprender+ Acesso

Aplicativo Android de tela de acesso para uma plataforma educacional fictícia, desenvolvido como projeto da Semana 07 do módulo de treinamento em Desenvolvimento de Aplicativos Móveis (Ocupação 08 – SENAI).

## Tecnologias

- Kotlin
- Jetpack Compose
- Material 3
- Android Studio
- Emulador Android

## Funcionalidades

- Alternância entre modo **Login** e modo **Cadastro**
- Validação em tempo real dos campos
- Mensagens de erro próximas a cada campo
- Senha oculta por padrão com toggle de visibilidade
- Checkbox "Manter-me conectado" (login) e "Aceito os termos de uso" (cadastro)
- Snackbar de feedback para sucesso e erro
- Dialog de confirmação ao cancelar ("Descartar dados?")
- Limpeza completa dos campos ao confirmar descarte

## Estrutura do projeto

```
com.example.aprenderacesso
├── ui
│   ├── screens
│   │   └── AccessScreen.kt
│   ├── components
│   │   ├── FormularioLogin.kt
│   │   └── FormularioCadastro.kt
│   ├── state
│   │   └── FormState.kt
│   └── theme
│       ├── Color.kt
│       ├── Theme.kt
│       └── Type.kt
├── validation
│   └── Validations.kt
└── MainActivity.kt
```

## Regras de validação

### E-mail (login e cadastro)
- Campo não pode estar vazio
- Deve conter `@`
- Domínio deve conter ponto após o `@`

### Senha (login e cadastro)
- Campo não pode estar vazio
- Mínimo de 6 caracteres
- Deve conter pelo menos uma letra e um número

### Confirmação de senha (cadastro)
- Campo não pode estar vazio
- Deve ser idêntica à senha digitada

### Nome completo (cadastro)
- Campo não pode estar vazio
- Mínimo de 10 caracteres
- Deve conter nome e sobrenome (pelo menos duas palavras)

### Termos de uso (cadastro)
- Checkbox deve estar marcado para concluir o cadastro

## Estado da tela

Todo o estado é gerenciado por `FormState`, uma `data class` com campos para valores, visibilidade de senha e erros. O estado é controlado diretamente em `AccessScreen` via `remember` e `mutableStateOf`, sem uso de ViewModel.

## Como executar

1. Clone o repositório
2. Abra no Android Studio
3. Sincronize o Gradle
4. Execute em um emulador Android (API 26+)


<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Página de Erro</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .error-container {
            text-align: center;
            background-color: #fff;
            padding: 40px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .error-container h1 {
            font-size: 4em;
            color: #e74c3c;
            margin: 0;
        }

        .error-container p {
            font-size: 1.2em;
            color: #666;
        }

        .error-container a {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #3498db;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s;
        }

        .error-container a:hover {
            background-color: #2980b9;
        }
    </style>
</head>

<body>
    <div class="error-container">
        <h1>Erro</h1>
        <p>Ocorreu um erro inesperado. Por favor, tente novamente mais tarde.</p>
        <a href="home">Voltar para a página inicial</a>
    </div>
</body>

</html>
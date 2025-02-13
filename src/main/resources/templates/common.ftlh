<#macro page>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>FinTools</title>
    <style>
        body, html {
            margin: 0;
            padding: 0;
            height: 100%;
        }
        .background-container {
            background-image: url('/images/fon.jpg'); /* Путь к изображению */
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            height: 100vh;

            color: black;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
        }
        .responsive-table {
            width: 100%;
            border-collapse: collapse;
        }
        .responsive-table th, .responsive-table td {
            border: 1px solid black;
            padding: 8px;
        }
        @media (max-width: 600px) {
            .responsive-table th, .responsive-table td {
                display: block;
                width: 100%;
            }
        }
    </style>

</head>
<body>
    <header>
        <nav>
            <ul>
                <div>
                    <li><a href="/">Главная</a></li>
                    <li><a href="/greeting">О нас</a></li>
                </div>
            </ul>
        </nav>
    </header>
    <#nested>
</body>
</html>
</#macro>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mensaje de Contacto</title>
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
            color: #222;
            margin: 0;
            padding: 20px;
            background: #f7f7f7;
        }
        .card {
            max-width: 640px;
            margin: 0 auto;
            background: #ffffff;
            border: 1px solid #e6e6e6;
            border-radius: 6px;
            padding: 16px 20px;
        }
        h2 {
            margin: 0 0 12px 0;
            font-size: 18px;
        }
        .row {
            margin: 8px 0;
        }
        .label {
            font-weight: bold;
        }
        .msg {
            margin-top: 12px;
            padding: 12px;
            background: #f2f6ff;
            border-left: 4px solid #2b6cb0;
        }
    </style>
</head>
<body>
    <div class="card">
        <h2>Nuevo mensaje de contacto</h2>
        <div class="row"><span class="label">Nombre:</span> ${name}</div>
        <div class="row"><span class="label">Email:</span> ${email}</div>
        <div class="row"><span class="label">Teléfono:</span> ${phone}</div>
        <div class="msg">${message}</div>
    </div>
</body>
</html>

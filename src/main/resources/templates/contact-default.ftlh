<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nuevo Mensaje de Contacto</title>
    <style>
        body {
            font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Helvetica, Arial, sans-serif;
            line-height: 1.6;
            color: #333;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 600px;
            margin: 20px auto;
            padding: 20px;
            background-color: #e2e2e2;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
        h1 {
            color: #2c3e50;
            text-align: center;
        }
        .message-section {
            border-top: 1px solid #eee;
            margin-top: 20px;
            padding-top: 20px;
        }
        .message-section p {
            margin: 0 0 10px;
        }
        .message-section p strong {
            color: #555;
            display: block;
            margin-bottom: 5px;
        }
        .message-content {
            background-color: #fafafa;
            border-left: 4px solid #3498db;
            padding: 15px;
            font-style: italic;
        }
        .footer {
            text-align: center;
            margin-top: 20px;
            font-size: 0.8em;
            color: #999;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>¡Nuevo Mensaje de Contacto!</h1>
        <p>Has recibido un nuevo mensaje desde el formulario de contacto de tu sitio web. A continuación, se detallan los datos del remitente y su mensaje:</p>

        <div class="message-section">
            <p><strong>Nombre:</strong> ${name}</p>
            <p><strong>Email:</strong> ${email}</p>
            <p><strong>Teléfono:</strong> ${phone}</p>
            <p><strong>Mensaje:</strong></p>
            <div class="message-content">
                <p>${message}</p>
            </div>
        </div>

        <div class="footer">
            <p>Este es un email automático, por favor no respondas a este correo.</p>
        </div>
    </div>
</body>
</html>

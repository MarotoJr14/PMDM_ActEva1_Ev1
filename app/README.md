# MiAppEjemplo
Aplicación Android nativa (Kotlin + XML) con 3 Activities:
- MainActivity: pantalla principal con logo y botón.
- PresentationActivity: muestra datos personales simulados y botón al formulario.
- ContactActivity: formulario con validaciones; al enviar, muestra Toast y envía datos a PresentationActivity.

## Requisitos
- Android Studio (Arctic Fox o superior)
- Kotlin plugin (incluido con Android Studio)
- SDK mínimo: 21

## Abrir y ejecutar
1. Clona el repositorio o descarga el proyecto.
2. Abre Android Studio → Open an existing project → selecciona la carpeta del proyecto.
3. Deja que Gradle sincronice.
4. Conecta un emulador o dispositivo y pulsa Run (▶).

## Capturas de pantalla
- `image/MainActivity.png`
- `image/PresentationActivity.png`
- `image/ContactActivity.png`

## Validaciones implementadas
- Nombre: obligatorio (no vacío).
- Email: obligatorio + patrón (`android.util.Patterns.EMAIL_ADDRESS`) para validar formato.
- Mensaje: obligatorio (no vacío).
- Si hay error, se marca `EditText.error` y se evita enviar.
- Al enviar: mostramos un `Toast` y pasamos los datos a `PresentationActivity` mediante Intent extras.

## Extras implementados
- Layouts con `ConstraintLayout`.
- `styles.xml` con colores y tipografías.
- App icon en `mipmap/`.
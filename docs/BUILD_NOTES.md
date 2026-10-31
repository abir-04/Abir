# Build Notes

## First time in Android Studio
1. Open the project
2. Let Gradle sync (it may download dependencies)
3. If you see missing icon errors, add a default `ic_launcher` in `app/src/main/res/mipmap-*` or change the manifest temporarily
4. Grant overlay permission on the device
5. Run on Android 8.0+

## Known first-build items
- Launcher icon may be missing (use default or generate one)
- Some layouts are created in code (no XML for every screen)
- AI responses are placeholders until you connect a real model

The project is structured so you can expand any module easily.

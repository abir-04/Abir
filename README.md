# Abir - My AI Assistant

Personal AI Assistant with overlay support for Mobile Legends (and other games), study mode, and modular features.

## Features Implemented (in progress)

### Overlay (Mobile Legends focus)
- Quick-question buttons (e.g. "Counter this hero", "Build tips")
- Hero picker
- Answer history in overlay
- Bubble size and opacity settings
- Auto-hide bubble
- Avatar boards and name effects unlocked by titles

### Other Features
- Titles and avatar boards full screen with progress
- Auto-detect other games
- Screen help (AI reads screen)
- Battery saver
- Study mode (textbook photos, practice questions, focus timer)
- Chat with history, search, pin, share, voice
- Voice settings
- Themes
- Accounts/sync (Firebase)
- Privacy settings
- Error screens and offline mode

## Project Structure
```
app/
  src/main/
    java/com/abir/assistant/
      overlay/          # Bubble service, settings
      mlbb/             # Mobile Legends specific (hero picker, counters)
      study/            # Study mode
      titles/           # Titles & avatar boards
      chat/             # Chat history
      core/             # Shared utils, AI interface
```

## Tech Stack
- Android (Kotlin)
- Overlay via WindowManager / SYSTEM_ALERT_WINDOW
- Modular feature modules
- Room for local storage (history, titles)
- Optional Firebase later

## Next
Core overlay + hero features being added.

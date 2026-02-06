# Building Tongram for Android

## Prerequisites

- Android Studio Ladybug (2024.2) or later
- JDK 17
- Android SDK 34
- Android NDK (version specified in `local.properties`)

## Steps

1. Clone the repository:
   ```bash
   git clone https://github.com/TONGRAM/tongram-ai-android.git
   cd tongram-ai-android
   ```

2. Create `local.properties` with your SDK path:
   ```
   sdk.dir=/path/to/Android/Sdk
   ndk.dir=/path/to/Android/Sdk/ndk/[version]
   ```

3. Obtain API credentials:
   - Register at https://my.telegram.org/apps
   - Get `api_id` and `api_hash`
   - Place in the appropriate config file

4. Build:
   ```bash
   ./gradlew assembleRelease
   ```

5. Output APK location:
   ```
   app/build/outputs/apk/release/
   ```

## Notes

- First build may take 15-30 minutes due to native library compilation
- Ensure sufficient disk space (10GB+ recommended)
- For development builds, use `./gradlew assembleDebug`

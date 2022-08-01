# Colorblind Helper
A RuneLite plugin which primarily aims to improve accessibility for colorblind individuals.

Currently, this plugin does two things:
- Draws `G` or `C` (customizable in the config) on grimy and clean herbs in your inventory
- When your character has the poison/venom/disease status effect, the heart in the HP minimap orb is replaced with a skull

Both of these can be disabled in the config if you find it unnecessary or intrusive.

## Running
You can run a RuneLite instance with the plugin installed by running `./gradlew testRun`. This will not install it to your main RuneLite instance unfortunately.

## Building
Because this plugin utilizes Kotlin instead of Java, it is incompatible with the original RuneLite client out of the box. You will need to either use a custom RuneLite-based client (like [OpenOSRS](https://github.com/open-osrs/runelite)) that has the Kotlin stdlib and reflection library included, or compile the plugin with Kotlin dependencies shaded into it.

### Compiling w/ Kotlin dependencies
If you are going to use this with the original RuneLite, you'll want to follow this.

Running `./gradlew shadowJar` will output a jar in `build/libs/` containing the Kotlin stdlib and other dependencies, making it compatible with RuneLite.

You will need to figure out a way to load the plugin into RuneLite.

### Compiling w/o Kotlin dependencies
If you are going to use this with a different client that provides Kotlin dependencies, you'll want to follow this.

Running `./gradlew jar` will output a jar in `build/libs/` without the Kotlin stdlib and other dependencies. From here, you can follow whatever steps are necessary to load a 3rd-party plugin into your client.
[api]: https://fluxpoint.dev/api

[releases]: https://github.com/FluxpointDev/Fluxpoint4J/releases
[jitpack]: https://jitpack.io

[versionBadge]: https://img.shields.io/github/v/release/fluxpointdev/Fluxpoint4J?label=Version&logo=github&style=for-the-badge

# Fluxpoint4J

Fluxpoint4J is the official Java wrapper for the [Fluxpoint API][api].

It is still under development, but already provides ways to create [custom images](#generate-custom-images) and [welcome images](#generate-welcome-images).

## Installation

[![versionBadge]][releases]

To install and use Fluxpoint4J will you need to add [jitpack] as a repository to your dependency manager of your choice and then declare the right version of Fluxpoint4J as dependency.

### Gradle

> **Note**  
> Replace `{latestVersion}` with the lates [version on Github][releases] (Shown above).

```groovy
repositories {
    maven { url = "https://jitpack.io" }
}

dependencies {
    implementation "com.github.fluxpointdev:Fluxpoint4J:{latestVersion}"
}
```

### Maven

> **Note**  
> Replace `{latestVersion}` with the lates [version on Github][releases] (Shown above).

```xml
<repositories>
    <repository>
        <id>jitpack</id>
        <url>https://jitpack.io/</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.fluxpointdev</groupId>
        <artifactId>Fluxpoint4J</artifactId>
        <version>{latestVersion}</version>
    </dependency>
</dependencies>
```

## Fluxpoint4J class

The `Fluxpoint4J` class is the main class used to get the images.

Before you can make any image will you need to create an instance of this class using the API token for the Fluxpoint API:  
```java
Fluxpoint4J api = new Fluxpoint4J();
api.setToken("my.s3cr3t.t0k3n");
```

## Generate custom images

> **Note**  
> Make sure to [create a Fluxpoint4J instance](#fluxpoint4j-class) first.

The Fluxpoint API allows users with an API-key to generate custom images using JSON.  
Fluxpoint4J was designed to support this JSON using dedicated classes.

The `CustomImage` class is used to create the JSON required for generating a custom image.  
It comes with a `Builder` sub-class that allows 3 things:

- Setting the `base` image used
- Adding any amount of extra images
- Adding any amount of Text (Single or multi line)

### Image and Text class

The `Image` and `Text` class are the main class used in creating custom images.  
Both have sub-classes that you should use.

While any of the `Text` sub-classes are optional is at least one `Image` sub-class required to create the `base` of the custom image:  
```java
Image.Rectangle base = new Image.Rectangle()
    .withColor(ColorObject.fromColor(Color.BLUE))
    .withWidth(300)
    .withHeight(400);

// Minimum required for a custom image.
CustomImage image = CustomImage.Builder.createBase(base).build();
```

The above example would create a Blue 300x400 rectangle.

Using the `addImage(Image)` and `addText(Text)` methods of `CustomImage.Builder` allows you to add extra images and text to the final custom image.

### Getting the image

To now get the image, you have to call the `getCustomImage(CustomImage)` or `queueCustomImage(CustomImage)` method in the `Fluxpoint4J` class.  
Both methods do the exact same thing, but `queueCustomImage(CustomImage)` returns a `CompletableFuture` of this process.

The returned value is a `GenericAPIResponse` that can either be an instance of `GeneratedImage` or `FailedAPIResponse`.  
You need to make `instanceof` checks to be sure that the returned instance is a Generated image or not.

Example:  
```java
public GeneratedImage getImage(CustomImage image){
    GenericAPIResponse response = getCustomImage(image);
    
    if(response instanceof FailedAPIResponse){
        System.out.println("Request FAILED!")
        return null;
    }
    
    return (GeneratedImage)response;
}
```

## Generate Welcome images

> **Note**  
> Make sure to [create a Fluxpoint4J instance](#fluxpoint4j-class) first.

You can use the `WelcomeImage.Builder` class to create a new `WelcomeImage` instance to use in either `getWelcomeImage(WelcomeImage)` or `queueWelcomeImage(WelcomeImage)`.

Only 3 methods are required to be used in the Builder class to create a valid WelcomeImage instance to use:

- `withUsername(String)`
- `withAvatar(String)`
- `withBackgroundColor(ColorObject)`

The returned value is a `GenericAPIResponse` that can either be an instance of `GeneratedImage` or `FailedAPIResponse`.  
You need to make `instanceof` checks to be sure that the returned instance is a Generated image or not.

Example:  
```java
public GeneratedImage getImage(WelcomeImage image){
    GenericAPIResponse response = getWelcomeImage(image);
    
    if(response instanceof FailedAPIResponse){
        System.out.println("Request FAILED!")
        return null;
    }
    
    return (GeneratedImage)response;
}
```

## Ping a MC Server

> **Note**  
> Make sure to [create a Fluxpoint4J instance](#fluxpoint4j-class) first.

You can ping a server and retrieve information from it by calling either `getMCServerInfo(String)`, `getMCServerInfo(String, int)` or one of their queue-equivalents.  
The first one is pinging the provided domain/IP on the default port (25565) while the second one allows you to set a port.

The returned value is a `GenericAPIResponse` that can either be an instance of `MCServerPingResponse` or `FailedAPIResponse`.  
You need to make `instanceof` checks to be sure that the returned instance is a Generated image or not.

Example:  
```java
public MCServerPingResponse getServerInfo(){
    GenericAPIResponse response = getMCServerInfo("mc.example.com");
    
    if(response instanceof FailedAPIResponse){
        System.out.println("Request FAILED!")
        return null;
    }
    
    return (MCServerPingResponse)response;
}
```
# Animations
## Introduction
This is a simple and straight-forward snippet that allows you to create animations and constant value changes in general.
You often have the problem that you need to linearly in- or decrement a value independently from the framerate, for example for a animation.
To simplify this and make it as easy and modular as possible, I created this utility class.

I hope you like it!

## Documentation
For a quickstart, see [General Usage](#general-usage), a more in-depth explanation is available in the javadoc. If you have any questions, just DM me on Discord! My tag is `_SIM_#6866` (UID: `505713760124665867`)
## General Usage
### Creating a Animation
Generally, you want to first construct a animation, save that object into a variable and use the `Animation#getValue` method from that point on to get the value of the animation.

For example, to make a logo animation in the Main Menu, you save a instance of the animation like this:
```java
private Animation animation = new Animation(/*Your arguments here, see "Creating a Animation"*/);
```
To create a animation, simply use one of the constructors!
There are currently four of them as listed and described here:
```java
// duration in seconds
Animation(float duration, float start, float end)

// duration in seconds
Animation(float duration, float start, float end, Easing easing)

// duration in milliseconds
Animation(long duration, float start, float end)

// duration in milliseconds
Animation(long duration, float start, float end, Easing easing)
```
As seen in the javadocs, putting in a `float` will assume the unit as `seconds`, putting in a `long` will assume the unit as `milliseconds`.

##### But what about the `fromChangePerSecond` method?
If you want to use a specific change in value per second rather than a fixed duration of the animation you can use this method **instead of the constructor (`new Animation()`)** to construct the Object.

### Using a Animation
To use a animation, you have to use the `getValue` method on a Animation object which got saved as described above. This will also take easings into account.

### Easings?
Now you might have read about these easings.
Generally, Easings mean that a value doesn't change linearly but rather start slow and end fast for example.
![A example of a easing](https://help.figma.com/hc/article_attachments/360082959894/EASE_IN_OUT_CURVE.gif)
To get a more in-depth documentation about easings; visit [easings.net](https://easings.net).
All the easings you can see there are availible in my Animation class!
To use them, just add `Easing.WHATEVEREASINGYOUWANT` as a argument to the constructor, to see which easings are existing, have a look in the `Easing` class.

### Resetting a Animation
Animations start at the exact moment they got constructed which means that if you save a animation far before using it the animation will already have a very late stage on first use. To prevent this, call the `reset` method **when the animation first gets shown**. If you use the animation almost immediatelly after constructing it (like a few milliseconds after it) this is not necessary. For example if you use it in the Main Menu and construct it at construction of the Gui (`private Animation animation = new Animation()` will create the animation around the time the constructor got called) the animation will get shown right after the initial constructor call = no reset needed since its such a short timespan.

*this part really needs improvement, it needs to be shortened down a lot*

## Examples
##### A animation with a duration of `1 second` ranging from `0` to `255`:
```java
new Animation(1F, 0, 255);
```
Explanation:
- `1F` 1 Second (because `F` casts it to float)
- `0` the start of the animation is 0
- `255` the end of the animation is 255

##### A animation with a duration of `420 milliseconds` ranging from `69.420` to `1337.420` with the easing `ease in out quart`:
```java
new Animation(420L, 69.420F, 1337.420F, Easing.EASE_IN_OUT_QUART);
```

Explanation:
- `420L` 420 Milliseconds (because `L` casts it to long)
- `69.420F` 69.420 is the start of the animation (`F` is required because floating point numbers are double per default in java and double and float isn't compatible)
- `1337.420F` the end of the animation is 1337.420, see above for why `F` is needed
- `Easing.EASE_IN_OUT_QUART` use the easing "ease in out quart"

Note: you don't have to change anything of the code, `Animation#getValue` will handle the easing stuff for you

## Contributing
If you have any ideas on how to improve this snippet (programmatically and this README) just send a Pull Request or file a Issue and i'll look into it! I'm always happy to improve this!

## Credits
- [Sixfalls](github.com/6ixfalls) for making the original Easings snippet, he was the one who got me into easings and inspired me to add easings to my own snippet
- [me (SIMULATAN)](https://github.com/SIMULATAN) for everything else :D
- you soon?

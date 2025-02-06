# Compare SemVer Formatted Version Numbers

This week's code compares two version numbers that follows the [Semantic Version](https://semver.org/) format, that
follows the `MAJOR`.`MINOR`.`PATCH` format for versioning software.

From the Semantic Version website:

> Given a version number MAJOR.MINOR.PATCH, increment the:
>
>  - MAJOR version when you make incompatible API changes
>  - MINOR version when you add functionality in a backward compatible manner
>  - PATCH version when you make backward compatible bug fixes
>  - Additional labels for pre-release and build metadata are available as extensions to the MAJOR.MINOR.PATCH format.

Here are some examples:

* `1.2.3`
* `4.34.2`
* `6.0.1-demo`
* `0.0.1-rc`

This week's code doesn't consider the additional labels part.


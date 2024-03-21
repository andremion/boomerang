// swift-tools-version:5.3
import PackageDescription

// BEGIN KMMBRIDGE VARIABLES BLOCK (do not edit)
let remoteKotlinUrl = "https://maven.pkg.github.com/andremion/boomerang/io/github/andremion/shared-kmmbridge/0.1.2/shared-kmmbridge-0.1.2.zip"
let remoteKotlinChecksum = "cfe74d30114873f11ab1ee37fdf8fb9700efad44ea4445f65167995b328421dd"
let packageName = "Shared"
// END KMMBRIDGE BLOCK

let package = Package(
    name: packageName,
    platforms: [
        .iOS(.v13)
    ],
    products: [
        .library(
            name: packageName,
            targets: [packageName]
        ),
    ],
    targets: [
        .binaryTarget(
            name: packageName,
            url: remoteKotlinUrl,
            checksum: remoteKotlinChecksum
        )
        ,
    ]
)
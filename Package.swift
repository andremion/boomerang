// swift-tools-version:5.3
import PackageDescription

// BEGIN KMMBRIDGE VARIABLES BLOCK (do not edit)
let remoteKotlinUrl = "https://maven.pkg.github.com/andremion/boomerang/boomerang/sample/shared-kmmbridge/unspecified/shared-kmmbridge-unspecified.zip"
let remoteKotlinChecksum = "4e885a1d92a46d82b269967bb156cdaf39f7acf9b7ec7642b2af0188af24ab40"
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
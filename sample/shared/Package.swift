// swift-tools-version:5.3
import PackageDescription

let packageName = "Shared"

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
            path: "./build/XCFrameworks/debug/\(packageName).xcframework"
        )
        ,
    ]
)
# Testcontainers Java Module Anvil

This library is a **Testcontainers module** for running an [Anvil](https://book.getfoundry.sh/anvil/) node inside a
**Docker container**.
It provides an easy way to spin up an Anvil test environment for **smart contract integration
testing** in Ethereum development.

## 🚀 Features

- **Easily launch an Anvil node** inside a Testcontainer.
- **Seamless integration with Kotlin and JUnit 5.**
- **Support for Ethereum forking** (e.g., Mainnet, Sepolia).
- **Pre-funded accounts for testing**.

## 🛠 Installation

### **Prerequisites**

- **Docker**
- **Kotlin 2.1.10+**
- **Gradle**

### **Add Dependency**

Add the following to your `build.gradle`:

TBD

## 📌 Running Tests with JUnit 5

```kotlin
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertTrue
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
class AnvilIntegrationTest {

  @Container
  private val anvil = AnvilContainer()

  @Test
  fun `test Anvil container is running`() {
    val rpcUrl = anvil.getRpcUrl()
    println("Anvil is running at: $rpcUrl")

    assertTrue(rpcUrl.startsWith("http"), "Invalid RPC URL")
  }
}
```

## 📜 License

This project is licensed under the [Apache 2.0](LICENSE).

## 🤝 Contributing

Contributions are welcome! Feel free to open an issue or submit a pull request.


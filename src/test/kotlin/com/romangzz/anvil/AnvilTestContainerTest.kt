package com.romangzz.anvil

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.testcontainers.utility.DockerImageName

class AnvilTestContainerIntegrationTest {

  companion object {
    private val anvilContainer =
        AnvilTestContainer(DockerImageName.parse("romangzz/anvil"))
            .withChainId(42)
            .withBlockTime(10)
            .withAccounts(5)
            .withBalance("5000000")

    @BeforeAll
    @JvmStatic
    fun startContainer() {
      anvilContainer.start()
    }

    @AfterAll
    @JvmStatic
    fun stopContainer() {
      anvilContainer.stop()
    }
  }

  @Test
  fun `should start Anvil test container and return valid RPC URL`() {
    val rpcUrl = anvilContainer.getRpcUrl()
    assertNotNull(rpcUrl)
    println("RPC URL: $rpcUrl")
  }

  @Test
  fun `should verify container default configuration`() {
    assertEquals("42", anvilContainer.envMap["CHAIN_ID"])
    assertEquals("10", anvilContainer.envMap["BLOCK_TIME"])
    assertEquals("5", anvilContainer.envMap["ACCOUNTS"])
    assertEquals("5000000", anvilContainer.envMap["BALANCE"])
  }

  @Test
  fun `should verify container logs contain listening message`() {
    val logs = anvilContainer.logs
    assert(logs.contains("Listening on 127.0.0.1:8545")) {
      "Container logs should indicate it is listening"
    }
  }
}

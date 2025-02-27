package com.romangzz.anvil

import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.wait.strategy.Wait
import org.testcontainers.utility.DockerImageName

class AnvilTestContainer : GenericContainer<AnvilTestContainer> {
  @Deprecated("Use constructor with DockerImageName instead")
  constructor() : super(DEFAULT_DOCKER_IMAGE.withTag(DEFAULT_TAG))

  constructor(dockerImageName: DockerImageName) : super(dockerImageName) {
    dockerImageName.assertCompatibleWith(DEFAULT_DOCKER_IMAGE)
  }

  constructor(dockerImageName: String) : super(DockerImageName.parse(dockerImageName))

  companion object {
    private val DEFAULT_DOCKER_IMAGE = DockerImageName.parse("romangzz/anvil")
    private const val DEFAULT_TAG = "latest"
    private const val DEFAULT_PORT = 8545
    private const val DEFAULT_CHAIN_ID = 1337
    private const val DEFAULT_ACCOUNTS = 10
    private const val DEFAULT_BALANCE = 10000
    private const val HTTP_PROTOCOL = "http"
  }

  init {
    withExposedPorts(DEFAULT_PORT)
    withEnv("CHAIN_ID", "$DEFAULT_CHAIN_ID")
    withEnv("ACCOUNTS", "$DEFAULT_ACCOUNTS")
    withEnv("BALANCE", "$DEFAULT_BALANCE")
    waitingFor(Wait.forLogMessage(".*Listening on 127.0.0.1:$DEFAULT_PORT.*", 1))
  }

  fun withChainId(chainId: Int): AnvilTestContainer {
    withEnv("CHAIN_ID", chainId.toString())
    return this
  }

  fun withBlockTime(seconds: Int): AnvilTestContainer {
    withEnv("BLOCK_TIME", seconds.toString())
    return this
  }

  fun withAccounts(count: Int): AnvilTestContainer {
    withEnv("ACCOUNTS", count.toString())
    return this
  }

  fun withBalance(balance: String): AnvilTestContainer {
    withEnv("BALANCE", balance)
    return this
  }

  fun getRpcUrl(): String {
    return "$HTTP_PROTOCOL://${host}:${getMappedPort(DEFAULT_PORT)}"
  }
}

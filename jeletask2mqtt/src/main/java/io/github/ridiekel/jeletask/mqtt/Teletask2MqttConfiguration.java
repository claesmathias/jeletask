package io.github.ridiekel.jeletask.mqtt;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "teletask")
public class Teletask2MqttConfiguration {

    private MqttConfiguration mqtt;

    private String configFile;
    private String version;
    private String host;
    private String id;
    private int port;
    private LoggingConfiguration log;
    private PublishConfiguration publish;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMqtt(MqttConfiguration mqtt) {
        this.mqtt = mqtt;
    }

    public String getConfigFile() {
        return this.configFile;
    }

    public void setConfigFile(String configFile) {
        this.configFile = configFile;
    }

    public MqttConfiguration getMqtt() {
        return this.mqtt;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public PublishConfiguration getPublish() {
        if (this.publish == null) {
            this.publish = new PublishConfiguration();
        }
        return publish;
    }

    public void setPublish(PublishConfiguration publish) {
        this.publish = publish;
    }

    public LoggingConfiguration getLog() {
        if (this.log == null) {
            this.log = new LoggingConfiguration();
        }
        return this.log;
    }

    public void setLog(LoggingConfiguration log) {
        this.log = log;
    }

    public static class MqttConfiguration {
        private String host;
        private String port;
        private String clientId;
        private String username;
        private String password;
        private String prefix;
        private boolean retained = false;
        private String discoveryPrefix;

        public boolean isRetained() {
            return retained;
        }

        public void setRetained(boolean retained) {
            this.retained = retained;
        }

        public String getHost() {
            return this.host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public String getPort() {
            return this.port;
        }

        public void setPort(String port) {
            this.port = port;
        }

        public String getClientId() {
            return this.clientId;
        }

        public void setClientId(String clientId) {
            this.clientId = clientId;
        }

        public String getUsername() {
            return this.username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return this.password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPrefix() {
            return this.prefix;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public String getDiscoveryPrefix() {
            return discoveryPrefix;
        }

        public void setDiscoveryPrefix(String discoveryPrefix) {
            this.discoveryPrefix = discoveryPrefix;
        }
    }

    public static class LoggingConfiguration {
        private boolean haconfigEnabled = false;
        private boolean topicEnabled = false;

        public boolean isHaconfigEnabled() {
            return haconfigEnabled;
        }

        public void setHaconfigEnabled(boolean haconfigEnabled) {
            this.haconfigEnabled = haconfigEnabled;
        }

        public boolean isTopicEnabled() {
            return topicEnabled;
        }

        public void setTopicEnabled(boolean topicEnabled) {
            this.topicEnabled = topicEnabled;
        }
    }

    public static class PublishConfiguration {
        private boolean motorPosition = true;
        private Integer motorPositionInterval = 250;

        public boolean isMotorPosition() {
            return motorPosition;
        }

        public void setMotorPosition(boolean motorPosition) {
            this.motorPosition = motorPosition;
        }

        public Integer getMotorPositionInterval() {
            return motorPositionInterval;
        }

        public void setMotorPositionInterval(Integer motorPositionInterval) {
            this.motorPositionInterval = motorPositionInterval;
        }
    }

}

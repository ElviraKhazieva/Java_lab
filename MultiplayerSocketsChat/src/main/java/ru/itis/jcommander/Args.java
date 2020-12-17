package ru.itis.jcommander;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=")
public class Args {
    @Parameter(names = {"--port"})
    public Integer port;
    @Parameter(names = {"--server-ip"})
    public String serverIp;
    @Parameter(names = {"--server-port"})
    public Integer serverPort;
}

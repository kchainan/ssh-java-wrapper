package org.skife.ssh;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class MuxerTest
{
    @Test
    public void testFoo() throws Exception
    {
        try (Muxer m = Muxer.withSocketsInTempDir()) {
            SSH ssh = m.connect("localhost")
                       .withArgs("-vv");

            ProcessResult r = ssh.exec("echo 'hello multiplexing world'");
            assertThat(r.getStdoutString()).isEqualTo("hello multiplexing world\n");
            assertThat(r.getStderrString()).contains("mux_client_request_session");
        }
    }
}

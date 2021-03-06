package com.alibaba.oneagent;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.instrument.Instrumentation;

import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.rule.OutputCapture;

import net.bytebuddy.agent.ByteBuddyAgent;

/**
 * 
 * @author hengyunabc 2020-09-17
 *
 */
public class BootstrapAgentTest {
    @Rule
    public OutputCapture capture = new OutputCapture();

    //@Test
    public void test() {
        Instrumentation instrumentation = ByteBuddyAgent.install();
        String args = AgentArgsUtils.agentArgs();

        System.err.println("args: " + args);
        BootstrapAgent.agentmain(args, instrumentation);

        BootstrapAgent.destory();

        assertThat(capture.toString()).contains("enabled TestActivator").contains("init TestActivator")
                .contains("start TestActivator").contains("start DubboDemoActivator").contains("DemoAgent started.")
                .contains("stop TestActivator");
    }

}

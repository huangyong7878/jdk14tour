package com.xfl;
import java.time.Duration;
import jdk.jfr.Configuration;
import jdk.jfr.consumer.*;
public class JEP349 {

  public void startCPU(){
    try (var rs = new RecordingStream()) {
      rs.enable("jdk.CPULoad").withPeriod(Duration.ofSeconds(1));
      rs.enable("jdk.JavaMonitorEnter").withThreshold(Duration.ofMillis(10));
      rs.onEvent("jdk.CPULoad", event -> {
        System.out.println(event.getFloat("machineTotal"));
      });
      rs.onEvent("jdk.JavaMonitorEnter", event -> {
        System.out.println(event.getClass("monitorClass"));
      });
      rs.onEvent("jdk.GarbageCollection",System.out::println);
      rs.onEvent("jdk.JVMInformation",System.out::println);
      rs.start();

    }
  }
  public void startGCMonitor()throws Exception{
    Configuration config = Configuration.getConfiguration("default");
    try (var es = new RecordingStream(config)) {
      es.onEvent("jdk.GarbageCollection", System.out::println);
      es.onEvent("jdk.CPULoad", System.out::println);
      es.onEvent("jdk.JVMInformation", System.out::println);
      es.setMaxAge(Duration.ofSeconds(10));
      es.start();
    }
  }
//  public void strem()throws Exception{
//
//
//    try(EventStream stream = EventStream.openRepository()){
//      stream.onEvent(recordedEvent -> {
//        recordedEvent.
//      });
//    }
//
//  }

  public static void main(String[] args)throws Exception{
    String jfrRepo = System.getProperty("jdk.jfr.repository");
    System.out.println(jfrRepo);
    JEP349 jep349 = new JEP349();
    jep349.startGCMonitor();
//    int a = 0;
//    for(int i=0;i<1000;i++){
//      a = i*i*a;
//    }
  }
}

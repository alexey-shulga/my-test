package example.micronaut;

import io.micronaut.core.optim.StaticOptimizations;
import io.micronaut.core.util.EnvironmentProperties;
import java.lang.Override;
import java.lang.String;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnvironmentPropertiesOptimizationLoader implements StaticOptimizations.Loader<EnvironmentProperties> {
  private void load0(Map<String, List<String>> env) {
    env.put("USERDOMAIN_ROAMINGPROFILE", Arrays.asList("userdomain.roamingprofile", "userdomain-roamingprofile"));
    env.put("PROCESSOR_LEVEL", Arrays.asList("processor.level", "processor-level"));
    env.put("SESSIONNAME", Arrays.asList("sessionname"));
    env.put("ALLUSERSPROFILE", Arrays.asList("allusersprofile"));
    env.put("PROCESSOR_ARCHITECTURE", Arrays.asList("processor.architecture", "processor-architecture"));
    env.put("PSModulePath", Arrays.asList("psmodulepath"));
    env.put("SystemDrive", Arrays.asList("systemdrive"));
    env.put("ACSvcPort", Arrays.asList("acsvcport"));
    env.put("USERNAME", Arrays.asList("username"));
    env.put("DIRNAME", Arrays.asList("dirname"));
    env.put("ProgramFiles(x86)", Arrays.asList("programfiles(x86)"));
    env.put("APP_HOME", Arrays.asList("app.home", "app-home"));
    env.put("PATHEXT", Arrays.asList("pathext"));
    env.put("DEFAULT_JVM_OPTS", Arrays.asList("default.jvm.opts", "default.jvm-opts", "default-jvm.opts", "default-jvm-opts"));
    env.put("DriverData", Arrays.asList("driverdata"));
    env.put("OneDriveConsumer", Arrays.asList("onedriveconsumer"));
    env.put("ProgramData", Arrays.asList("programdata"));
    env.put("ProgramW6432", Arrays.asList("programw6432"));
    env.put("HOMEPATH", Arrays.asList("homepath"));
    env.put("PROCESSOR_IDENTIFIER", Arrays.asList("processor.identifier", "processor-identifier"));
    env.put("PUBLIC", Arrays.asList("public"));
    env.put("ProgramFiles", Arrays.asList("programfiles"));
    env.put("windir", Arrays.asList("windir"));
    env.put("=::", Arrays.asList("=::"));
    env.put("OPENSSL_CONF", Arrays.asList("openssl.conf", "openssl-conf"));
    env.put("LOCALAPPDATA", Arrays.asList("localappdata"));
    env.put("USERDOMAIN", Arrays.asList("userdomain"));
    env.put("LOGONSERVER", Arrays.asList("logonserver"));
    env.put("PROMPT", Arrays.asList("prompt"));
    env.put("JAVA_HOME", Arrays.asList("java.home", "java-home"));
    env.put("RlsSvcPort", Arrays.asList("rlssvcport"));
    env.put("OneDrive", Arrays.asList("onedrive"));
    env.put("APPDATA", Arrays.asList("appdata"));
    env.put("=C:", Arrays.asList("=c:"));
    env.put("JAVA_EXE", Arrays.asList("java.exe", "java-exe"));
    env.put("Path", Arrays.asList("path"));
    env.put("CommonProgramFiles", Arrays.asList("commonprogramfiles"));
    env.put("OS", Arrays.asList("os"));
    env.put("COMPUTERNAME", Arrays.asList("computername"));
    env.put("PROCESSOR_REVISION", Arrays.asList("processor.revision", "processor-revision"));
    env.put("CommonProgramW6432", Arrays.asList("commonprogramw6432"));
    env.put("CLASSPATH", Arrays.asList("classpath"));
    env.put("ComSpec", Arrays.asList("comspec"));
    env.put("MICRONAUT_HOME", Arrays.asList("micronaut.home", "micronaut-home"));
    env.put("APP_BASE_NAME", Arrays.asList("app.base.name", "app.base-name", "app-base.name", "app-base-name"));
    env.put("TEMP", Arrays.asList("temp"));
    env.put("SystemRoot", Arrays.asList("systemroot"));
    env.put("USERPROFILE", Arrays.asList("userprofile"));
    env.put("HOMEDRIVE", Arrays.asList("homedrive"));
    env.put("TMP", Arrays.asList("tmp"));
    env.put("CommonProgramFiles(x86)", Arrays.asList("commonprogramfiles(x86)"));
    env.put("NUMBER_OF_PROCESSORS", Arrays.asList("number.of.processors", "number.of-processors", "number-of.processors", "number-of-processors"));
  }

  @Override
  public EnvironmentProperties load() {
    Map<String, List<String>> env = new HashMap<String, List<String>>();
    load0(env);
    return EnvironmentProperties.of(env);
  }
}

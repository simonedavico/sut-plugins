package cloud.benchflow.adapters.plugins.template;

import cloud.benchflow.adapters.api.WfMSPlugin;
import ro.fortsoft.pf4j.Plugin;
import ro.fortsoft.pf4j.PluginWrapper;

/**
 * @author Simone D'Avico (simonedavico@gmail.com)
 *
 * Created on 02/01/16.
 */
public class TemplatePlugin extends Plugin {


    public TemplatePlugin(PluginWrapper wrapper) {
        super(wrapper);
    }

    public static class WfMSExtension implements WfMSPlugin {

        public void start() {

        }

        public void deploy() {

        }
    }
}

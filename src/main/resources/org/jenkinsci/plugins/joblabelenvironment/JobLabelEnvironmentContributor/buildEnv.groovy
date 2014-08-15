package jenkins.model.CoreEnvironmentContributor;

def l = namespace(lib.JenkinsTagLib)

l.buildEnvVar(name: "JOB_LABELS") {
    raw(_("JOB_LABELS.blurb"))
}
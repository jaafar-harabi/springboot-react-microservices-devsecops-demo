{{- define "libsvc.labels" -}}
app.kubernetes.io/name: {{ include "libsvc.name" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
app.kubernetes.io/managed-by: Helm
{{- end -}}

{{- define "libsvc.name" -}}
{{- default .Chart.Name .Values.nameOverride | trunc 63 | trimSuffix "-" -}}
{{- end -}}

{{- define "libsvc.fullname" -}}
{{- if .Values.fullnameOverride -}}
{{- .Values.fullnameOverride | trunc 63 | trimSuffix "-" -}}
{{- else -}}
{{- printf "%s-%s" .Release.Name (include "libsvc.name" .) | trunc 63 | trimSuffix "-" -}}
{{- end -}}
{{- end -}}

{{- define "libsvc.container.env" -}}
{{- if .Values.env }}
{{- range $k, $v := .Values.env }}
- name: {{ $k }}
  value: "{{ $v }}"
{{- end }}
{{- end }}
{{- if .Values.envFrom }}
envFrom:
{{ toYaml .Values.envFrom | nindent 2 }}
{{- end }}
{{- end -}}

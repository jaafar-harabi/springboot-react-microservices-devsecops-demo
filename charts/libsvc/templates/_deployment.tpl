{{- define "libsvc.deployment" -}}
apiVersion: apps/v1
kind: Deployment
metadata:
  name: {{ include "libsvc.fullname" . }}
  labels:
    {{- include "libsvc.labels" . | nindent 4 }}
spec:
  replicas: {{ .Values.replicaCount }}
  selector:
    matchLabels:
      {{- include "libsvc.labels" . | nindent 6 }}
  template:
    metadata:
      labels:
        {{- include "libsvc.labels" . | nindent 8 }}
      annotations:
        {{- toYaml .Values.podAnnotations | nindent 8 }}
    spec:
      containers:
        - name: {{ include "libsvc.name" . }}
          image: "{{ required "image.repository is required" .Values.image.repository }}:{{ .Values.image.tag }}"
          imagePullPolicy: {{ .Values.image.pullPolicy }}
          ports:
            - containerPort: {{ .Values.service.targetPort }}
          env:
            {{- include "libsvc.container.env" . | nindent 12 }}
          resources:
            {{- toYaml .Values.resources | nindent 12 }}
{{- end -}}
